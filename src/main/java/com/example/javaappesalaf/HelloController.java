package com.example.javaappesalaf;
import java.io.IOException;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    public VBox Vbox1;
    @FXML
    public TextField tfId;
    @FXML
    public TextField tfTitle;
    @FXML
    public TextField tfAuthor;
    @FXML
    public TextField tfYear;
    @FXML
    public TextField tfNumber;
    @FXML
    public TableView<Books> tvBooks;
    @FXML
    public TableColumn<Books, Integer> tvId;
    @FXML
    public TableColumn<Books, String> tvTitle;
    @FXML
    public TableColumn<Books, String> tvAuthor;
    @FXML
    public TableColumn<Books, Integer> tvYear;
    @FXML
    public TableColumn<Books, Integer> tvNumber;
    @FXML
    public Button btnInsert;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button logout;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnInsert) {
            insertRecoerd();
        }
        if (event.getSource() == btnUpdate) {
            updateRecord();
        }
        if (event.getSource() == btnDelete) {
            deleteRecord();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        showBooks();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esalafapp","root","" );

            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ObservableList<Books> getBookList() {
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String req = "SELECT * FROM books";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(req);
            Books book;
            while (rs.next()) {
                book = new Books(rs.getInt("id"), rs.getString("author"), rs.getString("title"), rs.getInt("year"), rs.getInt("number"));
                bookList.add(book);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bookList;
    }
    public void showBooks(){
        ObservableList<Books> list = getBookList();
        tvId.setCellValueFactory(new PropertyValueFactory<Books , Integer>("id"));
        tvAuthor.setCellValueFactory(new PropertyValueFactory<Books , String>("author"));
        tvTitle.setCellValueFactory(new PropertyValueFactory<Books , String>("title"));
        tvYear.setCellValueFactory(new PropertyValueFactory<Books , Integer>("year"));
        tvNumber.setCellValueFactory(new PropertyValueFactory<Books , Integer>("number"));
        tvBooks.setItems(list);
    }
    private void insertRecoerd(){
        String req = "INSERT INTO BOOKS VALUES ("+tfId.getText()+", '"+
                tfAuthor.getText()+"' , '"+tfTitle.getText()+"' ,"+
                tfYear.getText()+","+tfNumber.getText()+")";
        executeQuery(req);
        showBooks();
    }
    private void updateRecord(){
        String req = "UPDATE books SET title = '"+tfTitle.getText()+"' , author = '"+
                tfAuthor.getText()+"' , year = "+tfYear.getText()+", number = "
                +tfNumber.getText()+" WHERE id = "+tfId.getText()+" ";
        executeQuery(req);
        showBooks();
    }
    private void deleteRecord(){
        String req = "DELETE FROM books WHERE id =" + tfId.getText() + " ";
        executeQuery(req);
        showBooks();
    }
    private void executeQuery(String req) {
        Connection cnx = getConnection();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(req);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    public void handleMouseAction(MouseEvent event) throws Exception {
        Books book = tvBooks.getSelectionModel().getSelectedItem();
        tfId.setText(""+book.getId());
        tfNumber.setText(""+book.getNumber());
        tfAuthor.setText(book.getAuthor());
        tfTitle.setText(book.getTitle());
        tfYear.setText(""+book.getYear());
    }
    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("sample.fxml");

    }
}