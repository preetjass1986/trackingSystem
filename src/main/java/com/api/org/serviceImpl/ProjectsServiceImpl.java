package com.api.org.serviceImpl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.org.constants.AppConstants;
import com.api.org.exception.BadRequestException;
import com.api.org.exception.RequiredParameterMissing;
import com.api.org.exception.ResourceNotFoundException;
import com.api.org.model.BomDetails;
import com.api.org.model.Projects;
import com.api.org.repository.BomDetailsRepository;
import com.api.org.repository.ProjectsRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ProjectsService;
import com.api.org.service.UserService;
import com.api.org.util.BomUploadError;
import com.api.org.util.CommonFunctions;
import com.api.org.view.Response;
import com.api.org.view.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;


@Service("ProjectsService")
public class ProjectsServiceImpl  implements ProjectsService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private ProjectsRepository projectsRepository;
	
	@Autowired
	private BomDetailsRepository bomDetailsRepository;
	
	
	@Override
    public Response project(UserPrincipal user,Long id)
    {
		if(id!=null)
		{
			Optional<Projects> projectsOpt=projectsRepository.findById(id);		
			
			if(projectsOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(projectsOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
			
    }
	
	@Override
	public Response project(UserPrincipal user)
	{
			Pageable pagable = PageRequest.of(0, 100);
			List<Projects>  projects=projectsRepository.getData(pagable);
			if(projects!=null && projects.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(projects);
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}
	
	@Override
	public Response manageProject(UserPrincipal user,Request request)
	{		
		
			if(request!=null )
			{
				if(request.getAction()==AppConstants.ONE) // add
				{
					if(!projectsRepository.existsByName(request.getName()))
					{
						projectsRepository.save(new Projects(request.getName(),request.getId(),request.getProductCode(),user.getId()));
						return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
					}
					else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
				}
				else if(request.getAction()==AppConstants.TWO) // update
				{
					Optional<Projects> projectsOpt=projectsRepository.findById(request.getId());
					if(projectsOpt.isPresent())
					{
						Projects project=projectsOpt.get();
						project.setName(request.getName());
						project.setUpdatedOn(new Date());
						projectsRepository.save(project);
						return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(project);	
					}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
				}
				else if(request.getAction()==AppConstants.THREE) // delete
				{
					Optional<Projects> projectsOpt=projectsRepository.findById(request.getId());
					if(projectsOpt.isPresent())
					{
						projectsRepository.delete(projectsOpt.get());
						return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
					}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
				}
				
				else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		
	}
	
	
	@Override
@Transactional
public Response manageProjectBOM(UserPrincipal user, Long projectId, MultipartFile file) {

    List<BomUploadError> errors = new ArrayList<>();
    List<BomDetails> validRows = new ArrayList<>();

    Set<String> excelDuplicateCheck = new HashSet<>();

    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        if (!rows.hasNext()) {
            throw new RuntimeException("Excel file is empty");
        }

        // Header validation
        Row headerRow = rows.next();
        validateHeader(headerRow);

        int rowNum = 1;

        while (rows.hasNext()) {
            rowNum++;
            Row row = rows.next();

            try {
                Long excelProjectId = Long.parseLong(getCellValue(row.getCell(0)));

                // Project ID cross-check
                if (!excelProjectId.equals(projectId)) {
                    throw new RuntimeException("Project ID mismatch");
                }

                String mother = getCellValue(row.getCell(1));
                String child = getCellValue(row.getCell(3));

                if (mother.isEmpty() || child.isEmpty()) {
                    throw new RuntimeException("Mother/Child Part No cannot be empty");
                }

                String key = bomKey(mother, child);
                if (!excelDuplicateCheck.add(key)) {
                    throw new RuntimeException("Duplicate Mother-Child combination in Excel");
                }

                // DB duplicate check
                if (bomDetailsRepository.existsByProjectIdAndMotherPartNoAndChildPartNo(
                        projectId, mother, child)) {
                    throw new RuntimeException("Duplicate BOM already exists in database");
                }

                BomDetails bom = new BomDetails();
                bom.setProjectId(projectId);
                bom.setMotherPartNo(mother);
                bom.setMotherPartDesc(getCellValue(row.getCell(2)));
                bom.setChildPartNo(child);
                bom.setChildPartDesc(getCellValue(row.getCell(4)));
                bom.setQuantity(getIntegerCellValue(row.getCell(5)));
                bom.setUnit(getCellValue(row.getCell(6)));
                bom.setType(getCellValue(row.getCell(7)));
                bom.setGaIssueLevel(getCellValue(row.getCell(8)));
                bom.setPlIssueLevel(getCellValue(row.getCell(9)));
                bom.setCreatedAt(LocalDateTime.now());

                validRows.add(bom);

            } catch (Exception e) {
                errors.add(new BomUploadError(rowNum, "ROW", e.getMessage()));
            }
        }

        if (!errors.isEmpty()) {
            return new Response()
                    .setResponseCode(AppConstants.BAD_REQUEST)
                    .setMessage("BOM upload failed with validation errors")
                    .setData(errors);
        }

        bomDetailsRepository.saveAll(validRows);

    } catch (Exception e) {
        throw new RuntimeException("BOM upload failed", e);
    }

    return new Response()
            .setResponseCode(AppConstants.SUCCESS)
            .setMessage("BOM uploaded successfully")
            .setData("Total rows inserted: " + validRows.size());
}

	private String getCellValue(Cell cell) {
	    if (cell == null) return null;
	    cell.setCellType(CellType.STRING);
	    return cell.getStringCellValue().trim();
	}

	private Integer getIntegerCellValue(Cell cell) {
	    if (cell == null) return 0;
	    if (cell.getCellType() == CellType.NUMERIC) {
	        return (int) cell.getNumericCellValue();
	    }
	    return Integer.parseInt(cell.getStringCellValue().trim());
	}
	
	private String bomKey(String mother, String child) {
	    return mother.trim() + "||" + child.trim();
	}

	private static final List<String> EXPECTED_HEADERS = Arrays.asList(
	        "Project ID",
	        "Mother Part No",
	        "Mother Part Description",
	        "Child Part No",
	        "Child Part Description",
	        "Quantity",
	        "Unit",
	        "Type",
	        "GA Issue Level",
	        "PL Issue Level"
	);

	private void validateHeader(Row headerRow) {
	    for (int i = 0; i < EXPECTED_HEADERS.size(); i++) {
	        String actual = headerRow.getCell(i).getStringCellValue().trim();
	        if (!EXPECTED_HEADERS.get(i).equalsIgnoreCase(actual)) {
	            throw new RuntimeException(
	                "Invalid Excel format. Expected column '" +
	                EXPECTED_HEADERS.get(i) + "' at position " + (i + 1)
	            );
	        }
	    }
	}



}
