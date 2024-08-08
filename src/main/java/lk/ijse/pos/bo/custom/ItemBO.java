package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto) throws SQLException;

    boolean updateItem(ItemDTO dto) throws SQLException;

    ItemDTO findItemById(int id) throws SQLException;

    boolean deleteItem(int id) throws SQLException;

    List<ItemDTO> findAllItems() throws SQLException;
}
