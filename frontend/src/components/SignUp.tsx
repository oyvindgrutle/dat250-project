import { Button, Center, FormControl, FormLabel, Heading, Input, Link, Text, VStack } from '@chakra-ui/react';
import React, { useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { authenticate } from '../api/api';
import Section from './Section';

interface Inputs {
    username: string;
    password: string;
    confirmPassword: string;
}

const SignIn = (): JSX.Element => {
    const {
        register,
        handleSubmit,
        formState: { isSubmitting },
    } = useForm<Inputs>();

    const [error, setError] = useState<string>('');

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        setError('');
        if (data.password !== data.confirmPassword) {
            setError('Deia må jo stemma? Eller gjer dei da?');
            return;
        }
        authenticate(data.username, data.password, true)
            .then((res) => setError('Success!'))
            .catch((err) => setError(err.message));
    };

    return (
        <Center>
            <Section mt="10%" w="40%">
                <Heading mb="2rem" color="red.500">
                    Sign up
                </Heading>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <VStack spacing="1rem">
                        <FormControl>
                            <FormLabel>Brukernavn</FormLabel>
                            <Input
                                id="username"
                                placeholder="username"
                                {...register('username', {
                                    required: 'This is required',
                                })}
                            />
                        </FormControl>
                        <FormControl>
                            <FormLabel>Passord</FormLabel>
                            <Input
                                id="password"
                                placeholder="password"
                                type="password"
                                {...register('password', {
                                    required: 'This is required',
                                })}
                            />
                        </FormControl>
                        <FormControl>
                            <FormLabel>Bekreft Passord</FormLabel>
                            <Input
                                id="confirmPassword"
                                placeholder="password"
                                type="password"
                                {...register('confirmPassword', {
                                    required: 'This is required',
                                })}
                            />
                        </FormControl>
                        {error && (
                            <Center>
                                <Text>{error}</Text>
                            </Center>
                        )}
                        <Center>
                            <Button type="submit" colorScheme="red" isLoading={isSubmitting}>
                                Sign up
                            </Button>
                        </Center>
                    </VStack>
                </form>
                <Center my="1rem">
                    <p>or</p>
                </Center>
                <Center>
                    <Link color="red" href="/signin">
                        Sign in
                    </Link>
                </Center>
            </Section>
        </Center>
    );
};

export default SignIn;
