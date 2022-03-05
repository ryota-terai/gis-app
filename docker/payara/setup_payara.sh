#!/bin/sh

asadmin --user admin --passwordfile /opt/payara/passwordFile add-resources glassfish-resources.xml
asadmin --user admin --passwordfile /opt/payara/passwordFile deploy /GHP2021App.ear
