import { Center, Text } from '@chakra-ui/react';
import React from 'react';
import useCountdown from '../hooks/useCountdown';

const Countdown = ({ date }: { date: Date }): JSX.Element => {
    const { hours, minutes, seconds } = useCountdown(date);

    return (
        <Center mt="3">
            <Text fontWeight="bold" fontSize={['5xl', null, null, '2xl', '3xl', '5xl']} textAlign="center">
                {hours < 10 ? `0${hours}` : hours} : {minutes < 10 ? `0${minutes}` : minutes} :{' '}
                {seconds < 10 ? `0${seconds}` : seconds}
            </Text>
        </Center>
    );
};

export default Countdown;
