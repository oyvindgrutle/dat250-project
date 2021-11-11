import { Box, Flex, Heading, Link, Spacer } from '@chakra-ui/react';
import React from 'react';

const Header = (): JSX.Element => (
    <Box px="2rem" bg="white">
        <Flex pt="60px" pb="1rem">
            <Link href="/">
                <Heading color="red.500">Paul Pollsen</Heading>
            </Link>
            <Spacer />
            <Link href="/signup">Sign up</Link>
            <Link href="/signin" ml="2rem">
                Sign in
            </Link>
        </Flex>
    </Box>
);

export default Header;
