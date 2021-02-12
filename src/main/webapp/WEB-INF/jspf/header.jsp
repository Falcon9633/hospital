<body class=".bg-secondary">

<nav class="navbar navbar-dark bg-dark navbar-expand-sm fixed-top">
    <div class="container">
        <span class="navbar-brand mb-0 h1"><fmt:message key="header.brand" bundle="${lang}"/></span>

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
                <a class="nav-link" href="#">Link 2</a>
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
                        <c:when test="${locale.name == 'EN'}">
                            ${accountDetails.nameEN} ${accountDetails.surnameEN}
                        </c:when>
                        <c:otherwise>
                            ${accountDetails.nameUA} ${accountDetails.surnameUA}
                        </c:otherwise>
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
