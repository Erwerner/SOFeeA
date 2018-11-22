package Engine;

import java.sql.SQLException;
import java.util.List;

import de.sofeea.datastructure.User;

public interface IMultiplierDB { 
	List<User> getAllUserInstances() throws SQLException; 
}
