package ru.kaznacheev.chat.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kaznacheev.chat.entity.Account;
import ru.kaznacheev.chat.repository.AccountRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserDetailsImpl(account.get());
    }

}
