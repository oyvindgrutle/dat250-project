import { Box } from '@chakra-ui/react';
import React from 'react';

interface Props {
    color: string;
    percentage: number;
}

const PollBar = ({ color, percentage }: Props) => (
    <Box bgColor={color} w={`${percentage}%`} h="3rem" borderRadius="1rem" />
);

export default PollBar;
