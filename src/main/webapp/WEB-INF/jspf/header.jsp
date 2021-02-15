<body class=".bg-secondary">

<nav class="navbar navbar-dark bg-dark navbar-expand-sm fixed-top opacity-3">
    <div class="container">
<%--        <span class="navbar-brand mb-0 h1"><fmt:message key="header.brand" bundle="${lang}"/></span>--%>
        <span class="navbar-brand">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" width="171" height="57" alt="">
        </span>

        <!-- Links -->
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                    <fmt:message key="header.admin.register_account" bundle="${lang}"/>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="controller?command=accountRegistrationAdmin">
                        <fmt:message key="header.admin.register_account.admin" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item" href="controller?command=accountRegistrationDoctor">
                        <fmt:message key="header.admin.register_account.doctor" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item" href="controller?command=accountRegistrationNurse">
                        <fmt:message key="header.admin.register_account.nurse" bundle="${lang}"/>
                    </a>
                    <a class="dropdown-item" href="controller?command=accountRegistrationPatient">
                        <fmt:message key="header.admin.register_account.patient" bundle="${lang}"/>
                    </a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=doctorSpecialization">
                    <fmt:message key="header.admin.doctor_specialization" bundle="${lang}"/>
                </a>
            </li>

            <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                    Dropdown link
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Link 1</a>
                    <a class="dropdown-item" href="#">Link 2</a>
                    <a class="dropdown-item" href="#">Link 3</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                    <c:choose>
                        <c:when test="${locale.name == 'en_US'}">
                            ${accountDetails.nameEN} ${accountDetails.surnameEN}
                        </c:when>
                        <c:when test="${locale.name == 'uk_UA'}">
                            ${accountDetails.nameUA} ${accountDetails.surnameUA}
                        </c:when>
                    </c:choose>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Link 1</a>
                    <a class="dropdown-item" href="#">Link 2</a>
                    <a class="dropdown-item" href="#">Link 3</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
