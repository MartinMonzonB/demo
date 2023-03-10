package lp3.backend.api;

import lp3.backend.model.Organization;
import lp3.backend.service.OrganizationService;
import lp3.backend.dao.OrganizationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/lp3/organization")
@RestController
public class OrganizationController {
    private final OrganizationService OrganizationService;
    @Autowired
    public OrganizationController(OrganizationService OrganizationService) {
        this.OrganizationService = OrganizationService;
    }
    @PostMapping
    public void addUser(@RequestBody Organization organization){
        OrganizationService.addOrganization(organization);
    }

    @GetMapping
    public List<Organization> getAllPeople(){
        return
                OrganizationService.getAllOrganizations();
    }

    @GetMapping(path = "{id}")
    public Organization getUserById(@PathVariable("id") UUID id){
        return OrganizationService.getOrganizationById(id)
            .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id) {
        OrganizationService.deleteOrganization(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @RequestBody Organization organizationToUpdate){
        OrganizationService.updateOrganization(id,organizationToUpdate);
    }
}