import {
    Box,
    Button,
    Center,
    Checkbox,
    FormControl,
    FormLabel,
    Heading,
    Input,
    Spinner,
    VStack,
} from '@chakra-ui/react';
import { parse } from 'date-fns';
import React, { useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { createPoll, postAnswer } from '../api/api';
import { useAuth } from '../context/AuthContext';
import Section from './Section';

interface Inputs {
    question: string;
    isPublic: boolean;
    startTime: string;
    endTime: string;
}

const CreatePoll = (): JSX.Element => {
    const authContext = useAuth();
    const {
        register,
        handleSubmit,
        formState: { isSubmitting },
        reset,
    } = useForm<Inputs>();

    const [error, setError] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(false);
    const [pollId, setPollId] = useState<string>('');

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        setError('');
        reset({ question: '', isPublic: false, startTime: '', endTime: '' });
        const submitData = async () => {
            if (authContext.account) {
                setLoading(true);
                console.log(parse(data.startTime, 'dd.LL.yyyy HH:mm', new Date()).getTime());
                console.log(parse(data.endTime, 'dd.LL.yyyy HH:mm', new Date()).getTime());
                const response = await createPoll(
                    data.question,
                    data.isPublic,
                    parse(data.startTime, 'dd.LL.yyyy HH:mm', new Date()).getTime(),
                    parse(data.endTime, 'dd.LL.yyyy HH:mm', new Date()).getTime(),
                    authContext.account.id,
                );
                const json = await response.json();
                if (response.ok) setPollId(json.id);
                else setError('Something went wrong');
                setLoading(false);
            }
        };
        submitData();
    };

    return (
        <Center>
            <Section mt="10%" w="40%">
                {!authContext.isAuthenticated && <Heading>You need to be signed in to create a poll!</Heading>}
                {authContext.isAuthenticated && (
                    <>
                        {loading && (
                            <Center>
                                <Spinner />
                            </Center>
                        )}
                        {!loading && (
                            <>
                                {error && <Heading>{error}</Heading>}
                                {pollId && !error && <Heading>You poll id is: {pollId}</Heading>}
                                {!pollId && !error && (
                                    <>
                                        <Heading color="red.500" textAlign="center" mb="2rem">
                                            Create poll
                                        </Heading>
                                        <form onSubmit={handleSubmit(onSubmit)}>
                                            <VStack spacing="1rem">
                                                <FormControl>
                                                    <FormLabel>Question</FormLabel>
                                                    <Input
                                                        id="question"
                                                        placeholder="question"
                                                        {...register('question', {
                                                            required: 'This is required',
                                                        })}
                                                    />
                                                </FormControl>
                                                <FormControl>
                                                    <FormLabel>Public Poll</FormLabel>
                                                    <Checkbox id="isPublic" {...register('isPublic')} />
                                                </FormControl>
                                                <FormControl>
                                                    <FormLabel>Starttime</FormLabel>
                                                    <Input
                                                        id="startTime"
                                                        placeholder="DD.MM.YYYY HH:MM"
                                                        {...register('startTime', {
                                                            required: 'This is required',
                                                        })}
                                                    />
                                                </FormControl>
                                                <FormControl>
                                                    <FormLabel>Endtime</FormLabel>
                                                    <Input
                                                        id="endtime"
                                                        placeholder="DD.MM.YYYY HH:MM"
                                                        {...register('endTime', {
                                                            required: 'This is required',
                                                        })}
                                                    />
                                                </FormControl>
                                                <Button type="submit">Submit</Button>
                                            </VStack>
                                        </form>
                                    </>
                                )}
                            </>
                        )}
                    </>
                )}
            </Section>
        </Center>
    );
};

export default CreatePoll;
