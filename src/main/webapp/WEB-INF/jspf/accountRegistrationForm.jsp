<input type="hidden" name="command" value="registerAccount"/>
<div class="form-group">
    <label for="login">
        <fmt:message key="account_registration.login.placeholder" var="loginPh" bundle="${lang}"/>
        ${loginPh}:
    </label>
    <input type="text" name="login" class="form-control" placeholder="${loginPh}" id="login"
           required minlength="5" maxlength="16" pattern="^(?![-_\d])((?![-_]{2})[\w-])+(?<![-_])$"/>
</div>
<div class="form-group">
    <label for="name_EN">
        <fmt:message key="account_registration.name_en.placeholder" var="nameEnPh" bundle="${lang}"/>
        ${nameEnPh}:
    </label>
    <input type="text" name="name_EN" class="form-control" placeholder="${nameEnPh}" id="name_EN"
           required minlength="2" maxlength="45" pattern="^[\w\d-_ ]+$"/>
</div>
<div class="form-group">
    <label for="surname_EN">
        <fmt:message key="account_registration.surname_en.placeholder" var="surnameEnPh" bundle="${lang}"/>
        ${surnameEnPh}:
    </label>
    <input type="text" name="surname_EN" class="form-control" placeholder="${surnameEnPh}" id="surname_EN"
           required minlength="2" maxlength="45" pattern="^[\w\d-_ ]+$"/>
</div>
<div class="form-group">
    <label for="name_UA">
        <fmt:message key="account_registration.name_ua.placeholder" var="nameUaPh" bundle="${lang}"/>
        ${nameUaPh}:
    </label>
    <input type="text" name="name_UA" class="form-control" placeholder="${nameUaPh}" id="name_UA"
           required minlength="2" maxlength="45" pattern="^[а-їѓА-ЯІЇЄЁ\d-_ ]+$"/>
</div>
<div class="form-group">
    <label for="surname_UA">
        <fmt:message key="account_registration.surname_ua.placeholder" var="surnameUaPh" bundle="${lang}"/>
        ${surnameUaPh}:
    </label>
    <input type="text" name="surname_UA" class="form-control" placeholder="${surnameUaPh}" id="surname_UA"
           required minlength="2" maxlength="45" pattern="^[а-їѓА-ЯІЇЄЁ\d-_ ]+$"/>
</div>
<div class="form-group">
    <label for="email">
        <fmt:message key="account_registration.email.placeholder" var="emailPh" bundle="${lang}"/>
        ${emailPh}:
    </label>
    <input type="email" name="email" class="form-control" placeholder="${emailPh}" id="email"
           required minlength="6" maxlength="45"/>
</div>
