import { Box, Flex, Heading, Link, Spacer, Text } from '@chakra-ui/react';
import React from 'react';
import { useAuth } from '../context/AuthContext';

const Header = (): JSX.Element => {
    const authContext = useAuth();

    return (
        <Box px="2rem" bg="white">
            <Flex pt="60px" pb="1rem">
                <Link href="/">
                    <Heading color="red.500">Paul Pollsen</Heading>
                </Link>
                <Spacer />
                {authContext.isAuthenticated && (
                    <>
                        <Text mr="2rem">Signed in as: {authContext.account?.username}</Text>
                        <Link onClick={authContext.logout}>Sign out</Link>
                    </>
                )}
                {!authContext.isAuthenticated && (
                    <>
                        <Link href="/signup">Sign up</Link>
                        <Link href="/signin" ml="2rem">
                            Sign in
                        </Link>
                    </>
                )}
            </Flex>
        </Box>
    );
};

export default Header;
