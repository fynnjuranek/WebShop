package de.leuphana.shop.behaviour;

import de.leuphana.connector.CustomerRestConnectorRequester;
import de.leuphana.shop.structure.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    CustomerRestConnectorRequester customerRestConnectorRequester;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Customer customer = customerRestConnectorRequester.getCustomerByEmail(emailAddress);
        if (customer != null) {
            // TODO: delete out-print
            System.out.println(customer.getCustomerEmail());
            return User.builder()
                    .username(customer.getCustomerEmail())
                    .password(customer.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(emailAddress);
        }
    }
}
