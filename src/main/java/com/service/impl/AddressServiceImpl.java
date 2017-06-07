package main.java.com.service.impl;

import main.java.com.dao.AddressDao;
import main.java.com.dao.impl.AddressDaoImpl;
import main.java.com.model.Address;
import main.java.com.service.AddressService;

/**
 * Created by gayathri on 6/7/17.
 */
public class AddressServiceImpl implements AddressService {

    private AddressDao addressDao;

    public AddressServiceImpl(){
        addressDao = new AddressDaoImpl();
    }

    public void setEmployeeDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public void configuration() {
        addressDao.configuration();
    }

    @Override
    public void save(Address employee) {
        addressDao.save(employee);
    }

}
