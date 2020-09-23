package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Address;

public interface AddressDao {
    ArrayList<Address> selectAddressByDong(String dong);
}
