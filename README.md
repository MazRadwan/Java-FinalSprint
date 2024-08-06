# Java Final Sprint

## Project Description

Java Final Sprint is a comprehensive Java-based application that includes various features such as user authentication, product management, and different dashboards for administrators, buyers, and sellers. The project follows a modular approach with clearly defined packages for data access, models, services, and GUI components.

## Directory Structure

```
root/
├── src/
│   └── main/
│       └── javafinalsprint/
│           ├── ConsoleApp.java
│           ├── Main.java
│           ├── dao/
│           │   ├── DatabaseConnection.java
│           │   ├── ProductDAO.java
│           │   └── UserDAO.java
│           ├── gui/
│           │   ├── AdminDashboard.java
│           │   ├── BuyerDashboard.java
│           │   ├── Dashboard.java
│           │   ├── LoginScreen.java
│           │   ├── ProductDetailsScreen.java
│           │   ├── RegistrationScreen.java
│           │   └── SellerDashboard.java
│           ├── model/
│           │   ├── Admin.java
│           │   ├── Buyer.java
│           │   ├── Product.java
│           │   ├── Seller.java
│           │   └── User.java
│           ├── service/
│           │   ├── ProductService.java
│           │   └── UserService.java
│           └── util/
│               └── ScreenUtils.java
├── .vscode/
│   └── tasks.json
├── javadoc_config.json
└── README.md
```

## Installation

1. **Clone the repository**

   ```sh
   git clone https://github.com/yourusername/java-finalsprint.git
   cd java-finalsprint
   ```

2. **Ensure you have JDK installed**

   - Download and install the JDK from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or use a package manager.

3. **Set up the environment variables**

   - Set `JAVA_HOME` to your JDK installation directory.
   - Add the JDK `bin` directory to your system `PATH`.

4. **Open the project in VSCode**

   - Open Visual Studio Code and open the project folder.

5. **Install necessary extensions**
   - Ensure you have the necessary extensions like Java Extension Pack installed in VSCode.

## Usage

### Running the Application

1. **Compile the project**

   - Use the integrated terminal in VSCode or your terminal:
     ```sh
     javac -d bin src/main/javafinalsprint/*.java src/main/javafinalsprint/dao/*.java src/main/javafinalsprint/gui/*.java src/main/javafinalsprint/model/*.java src/main/javafinalsprint/service/*.java src/main/javafinalsprint/util/*.java
     ```

2. **Run the main application**

   - Run the main class:
     ```sh
     java -cp bin javafinalsprint.Main
     ```

3. **Running the Console Application**

   - To launch the console application, run:
     ```sh
     java -cp bin javafinalsprint.ConsoleApp
     ```

4. **Launching the GUI Application**
   - To launch the GUI application, start from the LoginScreen:
     ```sh
     java -cp bin javafinalsprint.gui.LoginScreen
     ```

### Generating Javadocs

1. **Using VSCode Task**

   - Run the "Generate Javadocs" task from the VSCode command palette.

2. **Using Command Line**
   - Run the following command in the project root:
     ```sh
     javadoc -d docs -sourcepath src/main -subpackages javafinalsprint
     ```

### Features

- **User Authentication**
  - Allows users to register, login, and manage their accounts.
- **Product Management**

  - Provides functionalities to add, update, view, and delete products.

- **Dashboards**
  - Separate dashboards for administrators, buyers, and sellers with relevant functionalities.

## Contributing

1. **Fork the repository**
2. **Create a new branch**
   ```sh
   git checkout -b feature/your-feature
   ```
3. **Commit your changes**
   ```sh
   git commit -m "Add your message"
   ```
4. **Push to the branch**
   ```sh
   git push origin feature/your-feature
   ```
5. **Create a new Pull Request**

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions or suggestions, feel free to reach out.
