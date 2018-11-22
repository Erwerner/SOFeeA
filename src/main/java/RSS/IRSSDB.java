package RSS;
 
import java.sql.SQLException; 
import java.util.List;
  

public interface IRSSDB {  
	public List<RSSSource> getActiveSubscriptions() throws SQLException; 
	public void insertXMLcontent(RSSSource iSource) throws SQLException;
}
