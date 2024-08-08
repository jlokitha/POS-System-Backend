package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    private final ItemDAO itemDAO =
            (ItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException {
        return itemDAO.save(dto.toEntity());
    }
    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(dto.toEntity());
    }
    @Override
    public ItemDTO findItemById(int id) throws SQLException {
        Item data = itemDAO.getData(id);

        if (data != null) {
            return data.toDto();
        }
        return null;
    }
    @Override
    public boolean deleteItem(int id) throws SQLException {
        return itemDAO.delete(id);
    }
    @Override
    public List<ItemDTO> findAllItems() throws SQLException {
        List<Item> all = itemDAO.getAll();
        List<ItemDTO> dtos = new ArrayList<>();

        for (Item item : all) {
            dtos.add(item.toDto());
        }
        return dtos;
    }
}
