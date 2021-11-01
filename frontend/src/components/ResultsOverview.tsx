import { Box, Grid, Text } from '@chakra-ui/react';
import React from 'react';
import PollBar from './PollBar';

interface Props {
    numYes: number;
    numNo: number;
}

const ResultOverview = ({ numYes, numNo }: Props): JSX.Element => {
    const total = numYes + numNo;
    const percentageYes = (numYes / total) * 100;
    const percentageNo = (numNo / total) * 100;

    return (
        <Grid alignItems="center" mx="2rem" gap="1rem" textAlign="center" templateColumns="1fr 4fr">
            <Text fontSize="2rem" fontWeight="bold">
                YES
            </Text>
            <PollBar color="limegreen" percentage={percentageYes} />
            <Text fontSize="2rem" fontWeight="bold">
                NO
            </Text>
            <PollBar color="red" percentage={percentageNo} />
        </Grid>
    );
};

export default ResultOverview;
