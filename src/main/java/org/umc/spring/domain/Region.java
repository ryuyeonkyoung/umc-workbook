package org.umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.umc.spring.domain.common.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Store> stores = new HashSet<>();

    @Column(nullable = false, length = 50)
    private String name;

    public void addStore(Store store) {
        if (store == null || this.stores.contains(store)) return;
        this.stores.add(store);
        store.setRegion(this);
    }

    public void removeStore(Store store) {
        if (store == null || !this.stores.contains(store)) return;
        this.stores.remove(store);
        store.setRegion(null);
    }
}