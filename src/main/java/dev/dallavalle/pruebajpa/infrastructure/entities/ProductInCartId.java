package dev.dallavalle.pruebajpa.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductInCartId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "cart_id")
    private Long cartId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ProductInCartId that = (ProductInCartId) o;
        return getProductId() != null && Objects.equals(getProductId(), that.getProductId())
                && getCartId() != null && Objects.equals(getCartId(), that.getCartId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(productId, cartId);
    }
}
