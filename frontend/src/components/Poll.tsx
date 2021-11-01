import { Center, Flex, Heading, Spinner } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchPoll } from '../api/api';
import { getLocalStorageItem, setLocalStorageItem } from '../utils';
import AnswerButton from './AnswerButton';
import Section from './Section';

interface UrlParam {
    id: string;
}

interface Poll {
    id: number;
    question: string;
    startTime: string;
    endTime: string;
}

const getHasVoted = (id: string): boolean => {
    const hasVoted = getLocalStorageItem(`hasVoted_${id}`);
    return hasVoted === 'true';
};

const Poll = () => {
    const { id } = useParams<UrlParam>();

    const [poll, setPoll] = useState<Poll | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [hasVoted, setHasVoted] = useState<boolean>(getHasVoted(id));

    const handleVote = (choice: 'yes' | 'no') => {
        setHasVoted(true);
        setLocalStorageItem(`hasVoted_${id}`, 'true');
    };

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            const response = await fetchPoll(id);
            const data = await response.json();
            setPoll(data);
            setLoading(false);
        };
        fetchData();
    }, []);

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
                {poll && !hasVoted && (
                    <>
                        <Heading color="red.500" textAlign="center">
                            {poll.question}
                        </Heading>
                        <Flex
                            gridGap="1rem"
                            bg="gray.100"
                            p="1rem"
                            mt="2rem"
                            alignItems="center"
                            justifyContent="center"
                        >
                            <AnswerButton onClick={() => handleVote('yes')} buttonType="yes" />
                            <AnswerButton onClick={() => handleVote('no')} buttonType="no" />
                        </Flex>
                    </>
                )}
                {poll && hasVoted && <p>you have voted</p>}
            </Section>
        </Center>
    );
};

export default Poll;
