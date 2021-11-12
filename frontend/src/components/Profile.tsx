import { Heading } from '@chakra-ui/layout';
import React from 'react';
import { Redirect } from 'react-router';
import { useAuth } from '../context/AuthContext';
import Section from './Section';

const Profile = (): JSX.Element => {
    const authContext = useAuth();

    return (
        <>
            {!authContext.isAuthenticated ? (
                <Redirect to="/signin" />
            ) : (
                <Section mx="6rem" mt="3rem">
                    <Heading>{authContext.account?.username}</Heading>
                </Section>
            )}
        </>
    );
};

export default Profile;
