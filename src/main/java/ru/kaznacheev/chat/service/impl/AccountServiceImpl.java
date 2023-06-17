package ru.kaznacheev.chat.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kaznacheev.chat.entity.Account;
import ru.kaznacheev.chat.repository.AccountRepository;
import ru.kaznacheev.chat.service.AccountService;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registration(String username, String password) {
        Account newAccount = Account.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        accountRepository.save(newAccount);
    }

}
