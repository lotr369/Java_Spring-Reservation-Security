package kr.or.connect.reservation.user;

public class UserDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = 
			"SELECT id, name, password, email, create_date, modify_date FROM user WHERE email = :email";
}