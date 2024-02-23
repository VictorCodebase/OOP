import java.util.HashMap;
// Interface for managing tenants
interface TenantManagement {
    void addTenant(Tenant tenant);
    void removeTenant(String tenantId);
    Tenant findTenant(String tenantId);
    void displayAllTenants();
}

// Base class for Tenant
class Tenant {
    private String tenantId;
    private String name;
    private String email;
    
    public Tenant(String tenantId, String name, String email) {
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
    }
    
    // Getters and Setters
    public String getTenantId() {
        return tenantId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
}

// Subclass for ResidentialTenant
class ResidentialTenant extends Tenant {
    private int leaseDuration;

    public ResidentialTenant(String tenantId, String name, String email, int leaseDuration) {
        super(tenantId, name, email);
        this.leaseDuration = leaseDuration;
    }
    
    // Getter for lease duration
    public int getLeaseDuration() {
        return leaseDuration;
    }
}

// Subclass for CommercialTenant
class CommercialTenant extends Tenant {
    private String businessType;

    public CommercialTenant(String tenantId, String name, String email, String businessType) {
        super(tenantId, name, email);
        this.businessType = businessType;
    }
    
    // Getter for business type
    public String getBusinessType() {
        return businessType;
    }
}

// Implementation of Tenant Management
class TenantManager implements TenantManagement {
    private HashMap<String, Tenant> tenantMap;

    public TenantManager() {
        tenantMap = new HashMap<>();
    }

    @Override
    public void addTenant(Tenant tenant) {
        tenantMap.put(tenant.getTenantId(), tenant);
    }

    @Override
    public void removeTenant(String tenantId) {
        tenantMap.remove(tenantId);
    }

    @Override
    public Tenant findTenant(String tenantId) {
        return tenantMap.get(tenantId);
    }

    @Override
    public void displayAllTenants() {
        System.out.println("All Tenants:");
        for (Tenant tenant : tenantMap.values()) {
            System.out.println("ID: " + tenant.getTenantId() + ", Name: " + tenant.getName() + ", Email: " + tenant.getEmail());
        }
    }
}

public class RealEstateManagementApp {
    public static void main(String[] args) {
        TenantManager tenantManager = new TenantManager();

        // Adding some tenants
        tenantManager.addTenant(new ResidentialTenant("1001", "John Doe", "john@example.com", 12));
        tenantManager.addTenant(new CommercialTenant("2001", "ABC Corp", "abc@corp.com", "Technology"));
        
        // Displaying all tenants
        tenantManager.displayAllTenants();

        // Finding a tenant
        Tenant foundTenant = tenantManager.findTenant("1001");
        if (foundTenant != null) {
            System.out.println("Found Tenant: " + foundTenant.getName());
        } else {
            System.out.println("Tenant not found");
        }
    }
}
