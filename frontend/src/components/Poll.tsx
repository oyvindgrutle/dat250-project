import { Button, Center, Flex, Heading, Link, Spinner, Stack, Text, VStack } from '@chakra-ui/react';
import { isBefore, isFuture, isPast } from 'date-fns';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchPoll, postAnswer } from '../api/api';
import { Poll as IPoll } from '../lib/types';
import { getLocalStorageItem, setLocalStorageItem } from '../utils';
import AnswerButton from './AnswerButton';
import Countdown from './Countdown';
import useCountdown from '../hooks/useCountdown';
import ResultOverview from './ResultsOverview';
import Section from './Section';

interface UrlParam {
    id: string;
}

const getHasVoted = (id: string): boolean => {
    const hasVoted = getLocalStorageItem(`hasVoted_${id}`);
    return hasVoted === 'true';
};

const Poll = () => {
    const { id } = useParams<UrlParam>();

    const [poll, setPoll] = useState<IPoll | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [hasVoted, setHasVoted] = useState<boolean>(getHasVoted(id));

    const handleVote = (choice: boolean) => {
        const answer = {
            answer: choice,
            poll: {
                id: id,
            },
        };
        const postData = async () => {
            const response = await postAnswer(answer);
            if (response.ok) {
                setHasVoted(true);
                setLocalStorageItem(`hasVoted_${id}`, 'true');
            }
        };
        postData();
    };

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            const response = await fetchPoll(id);
            const data = await response.json();
            if (response.ok) setPoll(data);
            setLoading(false);
        };
        fetchData();
    }, [id, hasVoted]);

    if (!id) {
        return <p>No poll-code provided</p>;
    }

    return (
        <Center>
            <Section px="0" w="60%" mt="10%">
                {loading && (
                    <Center>
                        <Spinner />
                    </Center>
                )}
                {!poll && !loading && (
                    <Flex flexDirection="column" justifyContent="center" alignItems="center">
                        <Text fontSize="4rem">Poll not found</Text>
                        <Link href="/">Take me back</Link>
                    </Flex>
                )}
                {poll && !loading && (
                    <>
                        <Heading mb="2rem" color="red.500" textAlign="center">
                            {poll.question}
                        </Heading>
                        {!hasVoted && (
                            <>
                                {isFuture(new Date(poll.startTime)) && (
                                    <>
                                        <Heading textAlign="center">Poll opens in:</Heading>
                                        <Countdown date={new Date(poll.startTime)} />
                                    </>
                                )}
                                {isFuture(new Date(poll.endTime)) && isPast(new Date(poll.startTime)) && (
                                    <>
                                        <Flex
                                            gridGap="1rem"
                                            bg="gray.100"
                                            p="1rem"
                                            alignItems="center"
                                            justifyContent="center"
                                        >
                                            <AnswerButton onClick={() => handleVote(true)} buttonType="yes" />
                                            <AnswerButton onClick={() => handleVote(false)} buttonType="no" />
                                        </Flex>
                                        <Countdown date={new Date(poll.endTime)} />
                                    </>
                                )}

                                {isPast(new Date(poll.endTime)) && <Heading textAlign="center">Poll is closed</Heading>}
                            </>
                        )}
                        {hasVoted && <ResultOverview numYes={poll.numYes} numNo={poll.numNo} />}
                    </>
                )}
            </Section>
        </Center>
    );
};

export default Poll;
