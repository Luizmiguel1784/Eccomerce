package com.list.ecommerce.repository;

import com.list.ecommerce.entity.ItemDoPedido;
import com.list.ecommerce.entity.ItemDoPedidoPK;
import com.list.ecommerce.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, ItemDoPedidoPK> {

    ItemDoPedidoPK id(ItemDoPedidoPK id);
}
