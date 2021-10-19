package printed_products;

import lombok.*;
import printed_products.printed_product.PrintedProduct;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Newspaper extends PrintedProduct {}
