import { Box, Flex, Heading, Link, Spacer } from '@chakra-ui/react';
import React from 'react';

const Header = (): JSX.Element => (
    <Box px="2rem" bg="white">
        <Flex pt="60px" pb="1rem">
            <Heading color="red.500">Paul Pollsen</Heading>
            <Spacer />
            <Link>Sign up</Link>
            <Link ml="2rem">Sign in</Link>
        </Flex>
    </Box>
);

export default Header;
