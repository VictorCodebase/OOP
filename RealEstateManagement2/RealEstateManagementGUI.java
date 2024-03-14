import javax.swing.*;
import java.awt.*;// for GUI components
import java.awt.event.*; // for event handling
import java.util.HashMap; // for HashMap
import java.sql.*; // for SQL

interface TenantManagement {
    //Whats an interface?
    //An interface is a reference type in Java. It is similar to class. It is a collection of abstract methods. A class implements an interface, thereby inheriting the abstract methods of the interface.
    // An interface is a blueprint for a class. It has static, constant and abstract methods. It can be used to achieve fully abstraction and multiple inheritance.
    void addTenant(Tenant tenant);
    void removeTenant(String tenantId);
    Tenant findTenant(String tenantId);
    void displayAllTenants();
}

class Tenant {
    private String tenantId;
    private String name;
    private String email;
    // Why use private here?
    // The private keyword is an access modifier used for attributes, methods and constructors, making them only accessible within the declared class.
    // properties of Tenant class

    public Tenant(String tenantId, String name, String email) {
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
    }
    
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

class ResidentialTenant extends Tenant {
    private int leaseDuration;
    // hii ni property ya ResidentialTenant class

    public ResidentialTenant(String tenantId, String name, String email, int leaseDuration) {

        super( tenantId, name, email); // what does this do?
        // The super keyword refers to superclass (parent) objects. It is used to call superclass methods, and to access the superclass constructor.
        // Does this create the superclass?
        // Yes, it creates the superclass
        this.leaseDuration = leaseDuration;
    }
    //TODO: Understand how this works
    
    public int getLeaseDuration() {
        return leaseDuration;
    }
}

class CommercialTenant extends Tenant {
    private String businessType;
    // hii ni property ya CommercialTenant class

    public CommercialTenant(String tenantId, String name, String email, String businessType_) {
        // Constructor for CommercialTenant
        super(tenantId, name, email);
        this.businessType = businessType_;
    }
    
    public String getBusinessType() {
        return businessType;
    }
}



class TenantManager implements TenantManagement { 
    private HashMap<String, Tenant> tenantMap;

    public TenantManager() {
        tenantMap = new HashMap<>(); // hashmap is a class. So writing new hashmap creates a new instance of the class
    }

    @Override // what does this do?
    // The @Override annotation informs the compiler that the element is meant to override an element declared in a superclass. Overriding methods will be discussed in Interfaces and Inheritance.
    public void addTenant(Tenant tenant) {
        tenantMap.put(tenant.getTenantId(), tenant);
    }

    @Override
    public void removeTenant(String tenantId) {
        tenantMap.remove(tenantId);
    }

    @Override
    public Tenant findTenant(String tenantId) { //? Search for a tenant by their ID
        return tenantMap.get(tenantId);
    }

    @Override
    public void displayAllTenants() {
        StringBuilder sb = new StringBuilder("All Tenants:\n");
        for (Tenant tenant : tenantMap.values()) {
            sb.append("ID: ").append(tenant.getTenantId()).append(", Name: ").append(tenant.getName()).append(", Email: ").append(tenant.getEmail()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

class RealEstateManagementGUI extends JFrame {
    private JTextField tenantIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField typeField;
    private JButton addButton;
    private JButton displayButton;
    private TenantManager tenantManager;

    public RealEstateManagementGUI() {
        setTitle("Real Estate Management");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        tenantManager = new TenantManager();

        add(new JLabel("Tenant ID:"));
        tenantIdField = new JTextField();
        add(tenantIdField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Type (Residential/Commercial):"));
        typeField = new JTextField();
        add(typeField);

        addButton = new JButton("Add Tenant");
        addButton.addActionListener(new AddButtonListener());
        add(addButton);

        displayButton = new JButton("Display All Tenants");
        displayButton.addActionListener(new DisplayButtonListener());
        add(displayButton);
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tenantId = tenantIdField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String type = typeField.getText();

            if (type.equalsIgnoreCase("Residential")) {
                int leaseDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter lease duration:"));
                ResidentialTenant residentialTenant = new ResidentialTenant(tenantId, name, email, leaseDuration);
                tenantManager.addTenant(residentialTenant);
            } else if (type.equalsIgnoreCase("Commercial")) {
                String businessType = JOptionPane.showInputDialog("Enter business type:");
                CommercialTenant commercialTenant = new CommercialTenant(tenantId, name, email, businessType);
                tenantManager.addTenant(commercialTenant);
            } else {
                JOptionPane.showMessageDialog(null, "Residential or Commercial type required.");
            }
        }
    }

    private class DisplayButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tenantManager.displayAllTenants();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RealEstateManagementGUI app = new RealEstateManagementGUI();
            app.setVisible(true);
        });
    }
}


//SQL connectoion
