package spe.booker.booker;

public class user {
    private Integer userID;
    private String userEmail;
    private String userName;
    private String userFaculty;

    public user(Integer userID, String userEmail, String userName, String userFaculty) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userFaculty = userFaculty;
    }
}
