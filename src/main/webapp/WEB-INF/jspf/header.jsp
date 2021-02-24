<body class=".bg-secondary">

<nav class="navbar navbar-dark bg-dark navbar-expand-sm fixed-top opacity-3">
    <div class="container">
        <span class="navbar-brand">
            <img src="${pageContext.request.contextPath}/static/images/logo.png" width="171" height="57" alt="">
        </span>

        <c:if test="${role.name == 'admin'}">
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

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="header.admin.users" bundle="${lang}"/>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="controller?command=usersDoctors">
                            <fmt:message key="header.admin.users.doctors" bundle="${lang}"/>
                        </a>
                        <a class="dropdown-item" href="controller?command=usersPatients">
                            <fmt:message key="header.admin.users.patients" bundle="${lang}"/>
                        </a>
                    </div>
                </li>
            </ul>
        </c:if>

        <c:if test="${role.name == 'doctor'}">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" data-toggle="dropdown">
                        <fmt:message key="header.doctor.medical_cards" bundle="${lang}"/>
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="controller?command=doctorCurrentMedicalCards">
                            <fmt:message key="header.doctor.medical_cards.current" bundle="${lang}"/>
                        </a>
                        <a class="dropdown-item" href="controller?command=doctorAllMedicalCards">
                            <fmt:message key="header.doctor.medical_cards.all" bundle="${lang}"/>
                        </a>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="controller?command=doctorAssigment">
                        <fmt:message key="header.doctor.assignment" bundle="${lang}"/>
                    </a>
                </li>
            </ul>
        </c:if>

        <c:if test="${role.name == 'nurse'}">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=nurseAssignment">
                        <fmt:message key="header.nurse.assignment" bundle="${lang}"/>
                    </a>
                </li>
            </ul>
        </c:if>


        <ul class="navbar-nav ml-auto">
            <li class="nav-item mt-sm-2 mr-sm-1">
                <a class="flag-icon flag-icon-ua" href="${localUA}"></a><br>
                <a class="flag-icon flag-icon-us" href="${localEN}"></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown">
                        <c:if test="${locale.name == 'en_US'}">
                            ${accountDetails.nameEN} ${accountDetails.surnameEN}<br>
                            (${role.nameEN})
                        </c:if>
                        <c:if test="${locale.name == 'uk_UA'}">
                            ${accountDetails.nameUA} ${accountDetails.surnameUA}<br>
                            (${role.nameUA})
                        </c:if>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="controller?command=logout">
                        <fmt:message key="header.logout" bundle="${lang}"/>
                    </a>
                </div>
            </li>
        </ul>
    </div>
</nav>
