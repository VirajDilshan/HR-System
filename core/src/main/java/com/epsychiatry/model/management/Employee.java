package com.epsychiatry.model.management;

import com.epsychiatry.model.base.PersistentObject;
import com.epsychiatry.model.enums.Country;
import com.epsychiatry.model.enums.Department;
import com.epsychiatry.model.enums.Gender;
import com.epsychiatry.model.enums.Position;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends PersistentObject {
    @Column(name = "emp_id", unique = true, nullable = false, length = 100)
    @NotEmpty
    @NotNull
    private String empID;
    @Column(name = "nic", unique = true, nullable = false, length = 20)
    @NotEmpty
    @NotNull
    private String nic;
    @NotEmpty
    @NotNull
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "birthday")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "join_date")
    @NotNull
    private Date joinDate;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "country", length = 50)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Country country;
    @Column(name = "postal_code", length = 50)
    private String postalCode;
    @Column(name = "email", length = 150)
    @NotEmpty
    @NotNull
    private String email;
    @Column(name = "first_name", length = 50)
    @NotEmpty
    @NotNull
    private String firstName;
    @Column(name = "last_name", length = 50)
    @NotEmpty
    @NotNull
    private String lastName;
    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Gender gender;
    @Column(name = "mobile_no", length = 20)
    private String mobileNo;
    @Column(name = "land_no", length = 20)
    private String landNo;
    @Column(name = "bank_code", length = 10)
    private String bankCode;
    @Column(name = "bank_branch_code", length = 10)
    private String bankBranchCode;
    @Column(name = "bank_branch_name", length = 50)
    private String bankBranchName;
    @Column(name = "bank_account_number", length = 50)
    private String bankAccountNumber;

    @Column(name = "position")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Position position;

    @Column(name = "department")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Department department;

    @Column(name = "avatar")
    private String avatar = "esy_logo.jpg";

//    ##################################
    @Column(name = "username", unique = true, length = 50)
    private String username;
    @Column(name = "password",  length = 255)
    private String password;
    @Column(name = "enabled", columnDefinition = "TINYINT(1) default 0")
    private boolean enabled;
// #######################################
    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;

    //#####################################
    @OneToMany(
            mappedBy = "emp",
            fetch = FetchType.LAZY,
            cascade =  {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<Leave> leaves;

    @OneToMany(
            mappedBy = "approvedBy",
            fetch = FetchType.LAZY,
            cascade =  {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}
    )
    private Set<Leave> leavesApprovedBy;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLandNo() {
        return landNo;
    }

    public void setLandNo(String landNo) {
        this.landNo = landNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchCode(String bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(Set<Leave> leaves) {
        this.leaves = leaves;
    }

    public Set<Leave> getLeavesApprovedBy() {
        return leavesApprovedBy;
    }

    public void setLeavesApprovedBy(Set<Leave> leavesApprovedBy) {
        this.leavesApprovedBy = leavesApprovedBy;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Transient
    public String getAvatarImagePath() {
        if (getAvatar() == null || getId() == null) return null;
        return "/avatar/" + getId() + "/" + getAvatar();
    }
}
