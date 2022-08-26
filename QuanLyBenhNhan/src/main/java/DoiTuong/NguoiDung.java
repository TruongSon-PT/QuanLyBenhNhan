
package DoiTuong;

public class NguoiDung {
    private String userName;
    private String pass;
    private String chucVu;

    public NguoiDung() {
    }

    public NguoiDung(String userName, String pass, String chucVu) {
        this.userName = userName;
        this.pass = pass;
        this.chucVu = chucVu;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
}
