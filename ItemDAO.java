package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class ItemDAO {

  
    public void addItem(ItemDTO item) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

       
        Query<ItemDTO> query = session.createQuery(
                "from ItemDTO where name = :name", ItemDTO.class);
        query.setParameter("name", item.getName());

        if (!query.list().isEmpty()) {
            System.out.println(" Item already exists with this name!");
        } else {
            session.save(item);
            tx.commit();
            System.out.println(" Item added successfully!");
        }

        session.close();
    }

  
    public List<ItemDTO> getAllItems() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<ItemDTO> items = session
                .createQuery("from ItemDTO", ItemDTO.class)
                .list();

        session.close();
        return items;
    }

    // -------------------- UPDATE --------------------
    public void updateItem(int id, int option, double newValue) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        ItemDTO item = session.get(ItemDTO.class, id);

        if (item == null) {
            System.out.println(" Item not found!");
        } else {

            if (option == 1) {
                item.setPrice(newValue);
                System.out.println("Price updated!");
            } else if (option == 2) {
                item.setQuantity((int) newValue);
                System.out.println("Quantity updated!");
            } else {
                System.out.println("Invalid option!");
            }

            session.update(item);
            tx.commit();
        }

        session.close();
    }

 //delete
    public void deleteItem(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        ItemDTO item = session.get(ItemDTO.class, id);

        if (item != null) {
            session.delete(item);
            tx.commit();
            System.out.println(" Item deleted successfully!");
        } else {
            System.out.println(" Item not found!");
        }

        session.close();
    }
}
