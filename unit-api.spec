Name:          unit-api
Version:       1.0
Release:       3%{?dist}
Summary:       JSR 363 - Units of Measurement API
# JSR-363 has been approved as an official JCP standard (https://jcp.org/en/jsr/results?id=5877)
License:       BSD
URL:           http://unitsofmeasurement.github.io/
Source0:       https://github.com/unitsofmeasurement/unit-api/archive/%{version}/%{name}-%{version}.tar.gz

BuildRequires: maven-local
BuildRequires: maven-jar-plugin
BuildRequires: maven-install-plugin
BuildRequires: maven-surefire-plugin
BuildRequires: maven-surefire-provider-junit
BuildRequires: maven-verifier-plugin
BuildRequires: maven-dependency-plugin
BuildRequires: mvn(junit:junit)
BuildRequires: mvn(org.apache.felix:maven-bundle-plugin)
BuildRequires: mvn(org.sonatype.oss:oss-parent:pom:)

BuildArch:     noarch

%description
The Unit of Measurement library provides a set of
Java language programming interfaces for handling
units and quantities. The interfaces provide a layer
which separates client code, which would call the
API, from library code, which implements the API.

The specification contains Interfaces and abstract
classes with methods for unit operations:

* Checking of unit compatibility
* Expression of a quantity in various units
* Arithmetic operations on units

%package javadoc
Summary:       Javadoc for %{name}

%description javadoc
This package contains javadoc for %{name}.

%prep
%setup -q -n %{name}-%{version}
find . -name "*.class" -print -delete
find . -name "*.jar" -print -delete

# Unavailable plugins
%pom_remove_plugin :formatter-maven-plugin
%pom_remove_plugin :jacoco-maven-plugin
%pom_remove_plugin :license-maven-plugin

%mvn_file : %{name}

%build
%mvn_build

%install
%mvn_install

%files -f .mfiles
%doc README.md
%license LICENSE.txt

%files javadoc -f .mfiles-javadoc
%license LICENSE.txt

%changelog
* Tue Apr 04 2017 Nathan Scott <nathans@redhat.com> - 1.0-3
- Spec file changes for building on RHEL7.

* Sat Feb 11 2017 Fedora Release Engineering <releng@fedoraproject.org> - 1.0-2
- Rebuilt for https://fedoraproject.org/wiki/Fedora_26_Mass_Rebuild

* Fri Sep 16 2016 gil cattaneo <puntogil@libero.it> 1.0-1
- update to 1.0

* Wed Sep 02 2015 gil cattaneo <puntogil@libero.it> 0.7-1
- update to 0.7

* Sun Oct 20 2013 gil cattaneo <puntogil@libero.it> 0.6.2-0.1.RC1
- initial rpm
