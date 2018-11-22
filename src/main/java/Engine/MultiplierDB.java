package Engine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Infrastructure.DataMapper;
import de.sofeea.datastructure.AuthenticationData;
import de.sofeea.datastructure.User;

public class MultiplierDB implements IMultiplierDB {

	@Override
	public List<User> getAllUserInstances() throws SQLException {
		List<User> rUserList = new ArrayList<User>();
		DataMapper lDataMapper; 
		lDataMapper = new DataMapper();
		ResultSet lResultSet; 

		lResultSet = lDataMapper.executeSelect(
								"SELECT * from func_get_all_user();");
		while(lResultSet.next()){
			User lUser;
			AuthenticationData lAuthentication;
			Integer lUserID;
			String lLogin;
			String lPassword;
			String lConnectionstring;
			
			lUserID = lResultSet.getInt(1);
			lLogin  = lResultSet.getString(2);
			lPassword  = lResultSet.getString(3);
			lConnectionstring  = lResultSet.getString(4);
			
			lUser = new User(lUserID);
			lAuthentication = new AuthenticationData(lLogin,
													 lPassword,
													 lConnectionstring);
			lUser.setAuthentication(lAuthentication);
			
			rUserList.add(lUser);
		}
		return rUserList;
	} 
}