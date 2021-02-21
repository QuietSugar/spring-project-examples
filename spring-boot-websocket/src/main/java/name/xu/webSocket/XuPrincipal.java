package name.xu.webSocket;

import lombok.Data;

import java.security.Principal;

/**
 * @author Created by Xu
 */
@Data
public class XuPrincipal implements Principal {
    String name;
    String password;

    public XuPrincipal(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
