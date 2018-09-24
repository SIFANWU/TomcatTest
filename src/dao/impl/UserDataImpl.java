package dao.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import dao.UserDao;
import domain.User;
import utils.XmlUtils;


public class UserDataImpl implements UserDao {

	/* (non-Javadoc)
	 * @see dao.impl.UserDao#add(domain.User)
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void add(User user) {
		try {
			Document document = XmlUtils.getDocument();
			Element root = document.getRootElement();
			Element user_tag = root.addElement("user");
			
			user_tag.addAttribute("id", user.getId());
			user_tag.addAttribute("username", user.getUsername());
			user_tag.addAttribute("password", user.getPassword());
			user_tag.addAttribute("email", user.getEmail());
			DateFormat dateFormat = DateFormat.getDateInstance();
			user_tag.addAttribute("birthday",
					user.getBirthday() == null ? "" : dateFormat.format(user.getBirthday()));
			user_tag.addAttribute("account", "1000");
			
			XmlUtils.writeXml(document);

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}
	
	@Override
	public User pay(String username) {
		try {
			Document document = XmlUtils.getDocument();
			Element element = (Element) document.selectSingleNode("//user[@username='"+username+"']");

			User user = new User();
			Integer balance = Integer.valueOf(element.attributeValue("account"));
			balance= balance-4;
			user.setUsername(element.attributeValue("username"));
			user.setAccount(balance);
			element.addAttribute("account", balance.toString());
			
			if (!username.equals("wsf") && !username.equals("liyu")) {
				Element adminelement1 = (Element) document.selectSingleNode("//user[@username='wsf']");
				Element adminelement2 = (Element) document.selectSingleNode("//user[@username='liyu']");
				
				Integer balance2 =Integer.valueOf(adminelement1.attributeValue("account"))+2;
				adminelement1.addAttribute("account",balance2.toString());
				
				Integer balance3 =Integer.valueOf(adminelement2.attributeValue("account"))+2;
				adminelement2.addAttribute("account",balance3.toString());
			}
			if (username.equals("wsf")) {
				balance= balance+2;
				user.setUsername(element.attributeValue("username"));
				user.setAccount(balance);
				element.addAttribute("account", balance.toString());
				
				Element adminelement3 = (Element) document.selectSingleNode("//user[@username='liyu']");
				Integer balance4 =Integer.valueOf(adminelement3.attributeValue("account"))+2;
				adminelement3.addAttribute("account",balance4.toString());
			}
			if (username.equals("liyu")) {
				balance= balance+2;
				user.setUsername(element.attributeValue("username"));
				user.setAccount(balance);
				element.addAttribute("account", balance.toString());
				
				Element adminelement4 = (Element) document.selectSingleNode("//user[@username='wsf']");
				Integer balance5 =Integer.valueOf(adminelement4.attributeValue("account"))+2;
				adminelement4.addAttribute("account",balance5.toString());
			}
			
			
			
			
			
			XmlUtils.writeXml(document);
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username, String password) {
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document
					.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
			if (e == null) {
				return null;
			}
			User user= new User();
			
			String date = e.attributeValue("birthday");
			if (date == null || date.equals("")) {
				user.setBirthday(null);
			} else {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				user.setBirthday(df.parse(date));
			}
			
			user.setEmail(e.attributeValue("email"));
			user.setId(e.attributeValue("id"));
			user.setPassword(e.attributeValue("password"));
			user.setUsername(e.attributeValue("username"));
			user.setAccount(Integer.parseInt(e.attributeValue("account")));
	
			return user;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	  
	/* (non-Javadoc)
	 * @see dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String username) {
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@username='"+username+"']");
			if (e == null) {
				return false;
			}
			return true;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	
	
	

}
