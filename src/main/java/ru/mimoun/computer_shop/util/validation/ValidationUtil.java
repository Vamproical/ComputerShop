package ru.mimoun.computer_shop.util.validation;

import lombok.experimental.UtilityClass;
import ru.mimoun.computer_shop.HasId;
import ru.mimoun.computer_shop.error.IllegalRequestDataException;

import java.util.UUID;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, UUID id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (!bean.id().equals(id)) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}