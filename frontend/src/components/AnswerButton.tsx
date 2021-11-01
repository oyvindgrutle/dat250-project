import { Flex, Heading } from '@chakra-ui/react';
import React from 'react';

interface Props {
    onClick: () => void;
    buttonType: 'yes' | 'no';
}

const AnswerButton = ({ onClick, buttonType }: Props) => {
    return (
        <Flex
            onClick={onClick}
            transition=".1s ease-out"
            _hover={{ cursor: 'pointer', transform: 'scale(1.05)' }}
            w="50%"
            alignItems="center"
            justifyContent="center"
            p="5rem"
            bg={buttonType === 'yes' ? 'green.100' : 'blue.100'}
        >
            <Heading color={buttonType === 'yes' ? 'green.500' : 'blue.500'} fontSize="5rem">
                {buttonType.toUpperCase()}
            </Heading>
        </Flex>
    );
};

export default AnswerButton;
