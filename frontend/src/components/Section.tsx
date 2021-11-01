import { Box, BoxProps } from '@chakra-ui/react';
import React from 'react';

const Section = (props: BoxProps): JSX.Element => {
    return (
        <Box
            bg="white"
            p="6"
            overflow="hidden"
            shadow="lg"
            borderRadius="0.5rem"
            boxShadow="0 10px 20px 0 rgba(0, 0, 0, 0.1)"
            {...props}
        />
    );
};

export default Section;
