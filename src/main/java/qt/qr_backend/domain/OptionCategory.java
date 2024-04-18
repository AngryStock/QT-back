package qt.qr_backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "option_category_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "option_category_name")
    private String name;

    private boolean essential;
    private boolean only;

    public OptionCategory(Menu menuProxy, String optionCategory) {
        this.menu = menuProxy;
        this.name = optionCategory;
        this.essential = false;
        this.only = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEssential(boolean essential) {
        this.essential = essential;
    }

    public void setOnly(boolean only) {
        this.only = only;
    }
}
