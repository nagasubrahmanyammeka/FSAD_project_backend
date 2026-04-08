package com.agriconnect.config;

import com.agriconnect.entity.Product;
import com.agriconnect.entity.User;
import com.agriconnect.repository.ProductRepository;
import com.agriconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final UserRepository    userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder   passwordEncoder;

    @Override
    public void run(String... args) {
        seedAdminUser();
        seedSampleProducts();
    }

    // ─── Seed default admin ───────────────────────────────────────────
    private void seedAdminUser() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .name("Administrator")
                    .username("admin")
                    .email("admin@agriconnect.com")
                    .password(passwordEncoder.encode("admin123"))
                    .phone("9000000000")
                    .location("Hyderabad")
                    .role("admin")
                    .build();
            userRepository.save(admin);
            log.info("✅ Default admin user created  →  username: admin  |  password: admin123");
        }

        // Seed sample farmer for quick testing
        if (!userRepository.existsByUsername("farmer1")) {
            User farmer = User.builder()
                    .name("Ramesh Kumar")
                    .username("farmer1")
                    .email("ramesh@agriconnect.com")
                    .password(passwordEncoder.encode("farmer123"))
                    .phone("9876543210")
                    .location("Hyderabad")
                    .role("farmer")
                    .build();
            userRepository.save(farmer);
            log.info("✅ Sample farmer created        →  username: farmer1  |  password: farmer123");
        }

        // Seed sample expert
        if (!userRepository.existsByUsername("expert1")) {
            User expert = User.builder()
                    .name("Dr. Sita Reddy")
                    .username("expert1")
                    .email("sita@agriconnect.com")
                    .password(passwordEncoder.encode("expert123"))
                    .phone("9123456780")
                    .location("Chennai")
                    .role("expert")
                    .build();
            userRepository.save(expert);
            log.info("✅ Sample expert created        →  username: expert1  |  password: expert123");
        }

        // Seed sample public user
        if (!userRepository.existsByUsername("public1")) {
            User pub = User.builder()
                    .name("Arjun Sharma")
                    .username("public1")
                    .email("arjun@agriconnect.com")
                    .password(passwordEncoder.encode("public123"))
                    .phone("9012345678")
                    .location("Mumbai")
                    .role("public")
                    .build();
            userRepository.save(pub);
            log.info("✅ Sample public user created   →  username: public1  |  password: public123");
        }
    }

    // ─── Seed sample products ────────────────────────────────────────
    private void seedSampleProducts() {
        if (productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .name("Organic Fertilizer (50kg)")
                    .description("Premium compost-based organic fertilizer suitable for all crops.")
                    .price(850.0)
                    .category("Fertilizers")
                    .imageUrl("https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=400")
                    .stock(100)
                    .build());

            productRepository.save(Product.builder()
                    .name("Drip Irrigation Kit")
                    .description("Complete drip irrigation set for 1 acre with emitters and pipes.")
                    .price(4500.0)
                    .category("Irrigation")
                    .imageUrl("https://images.unsplash.com/photo-1464226184884-fa280b87c399?w=400")
                    .stock(25)
                    .build());

            productRepository.save(Product.builder()
                    .name("Neem Oil Pesticide (1L)")
                    .description("100% organic neem oil for pest control — safe for crops and soil.")
                    .price(320.0)
                    .category("Pesticides")
                    .imageUrl("https://images.unsplash.com/photo-1574943320219-553eb213f72d?w=400")
                    .stock(200)
                    .build());

            productRepository.save(Product.builder()
                    .name("pH Soil Testing Kit")
                    .description("Quick and accurate soil pH meter — essential for smart farming.")
                    .price(1200.0)
                    .category("Tools")
                    .imageUrl("https://images.unsplash.com/photo-1625246333195-78d9c38ad449?w=400")
                    .stock(60)
                    .build());

            productRepository.save(Product.builder()
                    .name("Hybrid Tomato Seeds (100g)")
                    .description("High-yield hybrid tomato seeds with disease resistance.")
                    .price(150.0)
                    .category("Seeds")
                    .imageUrl("https://images.unsplash.com/photo-1592921870789-04563d55041c?w=400")
                    .stock(500)
                    .build());

            log.info("✅ 5 sample products seeded into the database.");
        }
    }
}
