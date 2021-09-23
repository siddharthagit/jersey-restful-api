package jersey.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 * Service class for {@link Menu} Object
 * 
 * @author siddharthagit
 */
public class MenuService {

	private static MenuService instance = new MenuService();
	private static HashSet<Menu> menus;

	private MenuService() {
		menus = new HashSet<>();
		menus.add(new Menu(1L, "Menu One"));
	}

	public static MenuService getInstance() {
		return instance;
	}

	public void add(Menu menu) {
		menus.add(menu);
	}

	public List<Menu> getAll() {
		return new ArrayList(menus);
	}

	public Menu get(Long id) throws Exception {
		Iterator it = menus.iterator();
		while (it.hasNext()) {
			Menu curr = (Menu) it.next();
			if (curr.getId() == id)
				return curr;
		}
		throw new Exception("Object not found");
	}

	public boolean delete(Long id) throws Exception {
		Iterator it = menus.iterator();
		while (it.hasNext()) {
			Menu curr = (Menu) it.next();
			if (curr.getId() == id) {
				it.remove();
				return true;
			}
		}
		throw new Exception("Object not found");
	}
	
	public Menu update(Long id, String update) throws Exception {
		Iterator it = menus.iterator();
		while (it.hasNext()) {
			Menu curr = (Menu) it.next();
			if (curr.getId() == id) {
				curr.setName(update);
				return curr;
			}
				
		}
		throw new Exception("Object not found");
	}
}