import java.util.*;

/*
 * We developed a system that manages tenants in a real estate company.
 * The system can add different types of tenants, remove tenants, find tenants, and display all tenants.
 */

// Interface for managing tenants
interface TenantManagement {
    void addTenant(Tenant tenant);
    void removeTenant(String tenantId);
    Tenant findTenant(String tenantId);
    void displayAllTenants();
}

class Tenant {
    private String tenantId;
    private String name;
    private String email;
    
    // Constructor 
    public Tenant(String tenantId, String name, String email) {
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
    }
    
    // Getters 4 attributes
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
    
    public String getBusinessType() {
        return businessType;
    }
}

// Now this is mplementation of Tenant Management
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

class RealEstateManagement {
    public static void main(String[] args) {
        TenantManager tenantManager = new TenantManager();

        // Reading tenant details using a prompt
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter tenant ID: ");
        String tenantId = scanner.nextLine();

        System.out.print("Enter tenant name: ");
        String name = scanner.nextLine();

        System.out.print("Enter tenant email: ");
        String email = scanner.nextLine();

        System.out.print("Enter tenant type (Residential/Commercial): ");
        String tenantType = scanner.nextLine();

        // Handling different tenant types
        if (tenantType.equalsIgnoreCase("Residential")) {
            System.out.print("Enter lease duration: ");
            int leaseDuration = scanner.nextInt();
            scanner.nextLine(); 
            // a ResidentialTenant object
            ResidentialTenant residentialTenant = new ResidentialTenant(tenantId, name, email, leaseDuration);
            tenantManager.addTenant(residentialTenant);
        } else if (tenantType.equalsIgnoreCase("Commercial")) {
            System.out.print("Enter business type: ");
            String businessType = scanner.nextLine();
            //a CommercialTenant object
            CommercialTenant commercialTenant = new CommercialTenant(tenantId, name, email, businessType);
            tenantManager.addTenant(commercialTenant);
        } else {
            System.out.println("Invalid tenant type.");
        }

        // Display all tenants
        tenantManager.displayAllTenants();

        // Close the scanner
        scanner.close();
    }
}

