package cupet.com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="cupetshopproduct")
public class cupetshopproductVO {

    @Id
    @GeneratedValue
    public int cupet_prodno;

    @Column
    public String cupet_prodname;

    @Column
    public int cupet_prodprice;
    
    @Column
    public String cupet_prodcont;
    
    @Column
    public String cupet_prodimgpath;

    @Column
    public int cupet_proddiscountper;
}
