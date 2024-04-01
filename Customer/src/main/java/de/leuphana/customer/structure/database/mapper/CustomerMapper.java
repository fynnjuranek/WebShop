package de.leuphana.customer.structure.database.mapper;

import de.leuphana.customer.structure.database.entity.CartEntity;
import de.leuphana.customer.structure.database.entity.CartItemEntity;
import de.leuphana.customer.structure.database.entity.CustomerEntity;
import de.leuphana.customer.structure.Cart;
import de.leuphana.customer.structure.CartItem;
import de.leuphana.customer.structure.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity mapToCustomerEntity(Customer customer);
    Customer mapToCustomer(CustomerEntity customerEntity);

    CartEntity mapToCartEntity(Cart cart);
    Cart mapToCart(CartEntity cartEntity);

    CartItemEntity mapToCartItemEntity(CartItem cartItem);
    CartItem mapToCartItem(CartItemEntity cartItemEntity);




}
