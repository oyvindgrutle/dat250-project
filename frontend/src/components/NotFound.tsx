import { Box, Center, Heading, Text } from '@chakra-ui/react';
import React from 'react';

const NotFound = () => (
    <Center>
        <Box p="5rem" textAlign="center" mt="10%">
            <Heading color="red.500" fontSize="10rem">
                404
            </Heading>
            <Text>Denne siden eksisterer ikke</Text>
        </Box>
    </Center>
);

export default NotFound;
